package com.github.rnbr.invitevb.models;

import com.github.rnbr.invitevb.models.Member;
import java.util.Comparator;

/**
 * Usado para ordenar a lista de membros pelo nome.
 */
public class MemberComparator implements Comparator<Member> {

    @Override
    public int compare(Member a, Member b) {
        return a.getUsername().compareToIgnoreCase(b.getUsername());
    }
}