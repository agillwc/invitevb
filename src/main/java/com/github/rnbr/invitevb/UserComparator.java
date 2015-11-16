package com.github.rnbr.invitevb;

import java.util.Comparator;

/**
 * Usado para ordenar a lista de usuários pelo nome.
 */
class UserComparator implements Comparator<User> {

    @Override
    public int compare(User a, User b) {
        return a.getName().compareToIgnoreCase(b.getName());
    }

}