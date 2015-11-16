package com.github.rnbr.invitevb;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persistence {
    
    /**
     * Nome do arquivo onde serão salvos os usuários já convidados.
     */
    private final String FILE_NAME = "invited.txt";
    
    /**
     * Caractere para separar as informações (nome de usuário e o link do perfil)
     * no arquivo de texto.
     */
    private final String SEPARATOR = ";";
    
    /**
     * Localização onde o arquivo deve ser criado.
     * Essa informação está no arquivo {@code 'invite.properties'}.
     */
    private final Path FILE_PATH;

    public Persistence() {
        this.FILE_PATH = Paths.get(Settings.get().getProperty("save.location"));
    }
    
    /**
     * Faz a gravação dos usuários em um arquivo de texto.
     * @see Persistence#FILE_PATH Path onde o arquivo será criado.
     * @see Persistence#FILE_NAME Nome do arquivo.
     * @see Persistence#SEPARATOR Separador de informações dentro do arquivo.
     * @param users é a lista a ser armazenada.
     */
    public void write(List<User> users){
        List<User> readedUsers = read();
        if(!readedUsers.isEmpty())
            users = removeDuplicates(users, readedUsers, true);
        
        StringBuilder builder = new StringBuilder();
        for(User user: users)
            builder.append(user.getName())
                   .append(SEPARATOR)
                   .append(user.getProfile())
                   .append(System.lineSeparator());
        try {
            Files.write(FILE_PATH, builder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {}
    }
    
    
    /**
     * Faz a leitura do conteúdo do arquivo e tenta construir uma lista de usuários.
     * @return uma lista de {@code User} se o conteúdo estiver corretamente formatado.
     */
    public List<User> read(){
        List<User> users = new ArrayList<>();
        Path fullPath = Paths.get(FILE_PATH + Character.toString(File.separatorChar) + FILE_NAME);
        
        /**
         * O arquivo não existe.
         * Então é retornada uma lista vazia.
         */
        if(!Files.exists(fullPath, LinkOption.NOFOLLOW_LINKS)){
            try {
                fullPath.toFile().createNewFile();
            } catch (IOException ex) {}
            return users;
        }

        /**
         * O arquivo existe.
         * Então tenta-se obter os dados em forma de objetos {@code User}.
         */
        try {
            List<String> lines = Files.readAllLines(fullPath, StandardCharsets.UTF_8);
            if(!lines.isEmpty()){
                for(String line : lines){
                    final String contents[] = line.split(SEPARATOR);
                    users.add(new User(contents[0], contents[1]));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Persistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    /**
     * Remove os itens duplicados de duas listas.
     * @param users 
     * @param anotherUsers
     * @param sort se é necessário ordenar a lista de usuários pelo atributo {@code name}.
     * @return 
     */
    public ArrayList<User> removeDuplicates(List<User> users, List<User> anotherUsers, boolean sort){
        Set<User> userSet = new LinkedHashSet<>();
        userSet.addAll(users);
        userSet.addAll(anotherUsers);
        users.clear();
        users.addAll(userSet);
       
        if(sort)
            Collections.sort(users, new UserComparator());
        return new ArrayList<>(users);
    }
}