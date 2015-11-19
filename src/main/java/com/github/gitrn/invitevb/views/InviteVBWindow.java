package com.github.gitrn.invitevb.views;

import com.github.gitrn.invitevb.modes.SendMode;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class InviteVBWindow extends javax.swing.JFrame {

    public InviteVBWindow() {
        initComponents();
        initialize();
    }
    
    private void initialize(){
        // Centralizar Janela.
        setLocationRelativeTo(null);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException err){}
    }
    
    /**
     * Alterna o estado do botão.
     */
    public void switchButtonState(){
        btAction.setText("Interromper e sair");
        btAction.setActionCommand("stop");
    }

    /**
     * @return o botão de ação para iniciar/interromper o envio.
     */
    public JButton getBtAction() {
        return btAction;
    }

    /**
     * @return o campo de 'modo de envio'.
     */
    public JComboBox<SendMode> getCbMode() {
        return cbMode;
    }

    /**
     * @return o campo de 'homepage'. 
     */
    public JTextField getFdHomepage() {
        return fdHomepage;
    }

    /**
     * @return o campo de 'nome de usuário'.
     */
    public JTextField getFdUsername() {
        return fdUsername;
    }

    /**
     * @return o campo de 'senha'.
     */
    public JPasswordField getFdPassword() {
        return fdPassword;
    }

    /**
     * @return o campo 'mensagem'.
     */
    public JTextArea getFdMessage() {
        return fdMessage;
    }

    /**
     * @return o campo 'intervalo'. 
     */
    public JTextField getFdInterval() {
        return fdInterval;
    }

    /**
     * @return a barra de progresso.
     */
    public JProgressBar getPbProgress() {
        return pbProgress;
    }
    
    public void setMessage(String content){
        lbProgress.setText("<html>" + content + "</html>");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        lbUsername = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        fdUsername = new javax.swing.JTextField();
        fdPassword = new javax.swing.JPasswordField();
        fdHomepage = new javax.swing.JTextField();
        lbHomepage = new javax.swing.JLabel();
        cbMode = new javax.swing.JComboBox<>();
        lbMode = new javax.swing.JLabel();
        btAction = new javax.swing.JButton();
        pbProgress = new javax.swing.JProgressBar();
        lbProgress = new javax.swing.JLabel();
        spMessage = new javax.swing.JScrollPane();
        fdMessage = new javax.swing.JTextArea();
        lbMessage = new javax.swing.JLabel();
        lbInterval = new javax.swing.JLabel();
        lbSeconds = new javax.swing.JLabel();
        fdInterval = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("InviteVB - build 0.1.1");
        setResizable(false);

        rootPanel.setBackground(new java.awt.Color(255, 255, 255));

        lbUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbUsername.setForeground(new java.awt.Color(102, 102, 102));
        lbUsername.setLabelFor(fdUsername);
        lbUsername.setText("Nome de usuário:");

        lbPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbPassword.setForeground(new java.awt.Color(102, 102, 102));
        lbPassword.setLabelFor(fdPassword);
        lbPassword.setText("Senha:");

        fdUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fdUsername.setToolTipText("Nome de usuário no fórum");

        fdPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fdPassword.setToolTipText("Senha de acesso ao fórum");

        fdHomepage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fdHomepage.setToolTipText("Index do fórum");

        lbHomepage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbHomepage.setForeground(new java.awt.Color(102, 102, 102));
        lbHomepage.setLabelFor(fdHomepage);
        lbHomepage.setText("Homepage:");

        cbMode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbMode.setModel(new javax.swing.DefaultComboBoxModel<>(SendMode.values()));

        lbMode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbMode.setForeground(new java.awt.Color(102, 102, 102));
        lbMode.setLabelFor(cbMode);
        lbMode.setText("Modo de envio:");

        btAction.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btAction.setText("Iniciar");
        btAction.setActionCommand("run");
        btAction.setFocusable(false);

        pbProgress.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lbProgress.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lbProgress.setForeground(new java.awt.Color(102, 102, 102));
        lbProgress.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbProgress.setText("<html>Bugs, dúvidas e ajuda, só no Github. Crie um issue em: <b>https://github.com/4ucheats/invitevb/issues</b></html>");

        spMessage.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 255, 255))));

        fdMessage.setColumns(20);
        fdMessage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fdMessage.setLineWrap(true);
        fdMessage.setRows(5);
        fdMessage.setWrapStyleWord(true);
        fdMessage.setBorder(null);
        spMessage.setViewportView(fdMessage);

        lbMessage.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbMessage.setForeground(new java.awt.Color(102, 102, 102));
        lbMessage.setLabelFor(fdPassword);
        lbMessage.setText("Mensagem:");

        lbInterval.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbInterval.setForeground(new java.awt.Color(102, 102, 102));
        lbInterval.setLabelFor(fdPassword);
        lbInterval.setText("Intervalo:");

        lbSeconds.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        lbSeconds.setForeground(new java.awt.Color(102, 102, 102));
        lbSeconds.setLabelFor(fdPassword);
        lbSeconds.setText("segundos.");

        fdInterval.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fdInterval.setToolTipText("Intervalo entre as mensagens");

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbProgress)
                    .addComponent(spMessage)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btAction, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(lbMessage)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbUsername)
                            .addComponent(lbPassword)
                            .addComponent(lbInterval))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(rootPanelLayout.createSequentialGroup()
                                .addComponent(fdInterval, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbSeconds))
                            .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(fdPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                .addComponent(fdUsername)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rootPanelLayout.createSequentialGroup()
                                .addComponent(lbMode, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(rootPanelLayout.createSequentialGroup()
                                .addComponent(lbHomepage)
                                .addGap(35, 35, 35)))
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fdHomepage)
                            .addComponent(cbMode, 0, 160, Short.MAX_VALUE))))
                .addContainerGap())
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUsername)
                    .addComponent(fdUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbPassword))
                    .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fdHomepage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbHomepage)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fdInterval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbSeconds))
                    .addComponent(lbInterval))
                .addGap(18, 18, 18)
                .addComponent(lbMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btAction)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(lbProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pbProgress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rootPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAction;
    private javax.swing.JComboBox<SendMode> cbMode;
    private javax.swing.JTextField fdHomepage;
    private javax.swing.JTextField fdInterval;
    private javax.swing.JTextArea fdMessage;
    private javax.swing.JPasswordField fdPassword;
    private javax.swing.JTextField fdUsername;
    private javax.swing.JLabel lbHomepage;
    private javax.swing.JLabel lbInterval;
    private javax.swing.JLabel lbMessage;
    private javax.swing.JLabel lbMode;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbProgress;
    private javax.swing.JLabel lbSeconds;
    private javax.swing.JLabel lbUsername;
    private javax.swing.JProgressBar pbProgress;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JScrollPane spMessage;
    // End of variables declaration//GEN-END:variables
}