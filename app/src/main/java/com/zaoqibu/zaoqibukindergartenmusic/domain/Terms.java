package com.zaoqibu.zaoqibukindergartenmusic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vwarship on 2014/11/27.
 */
public class Terms implements Serializable {
    private List<Term> terms;

    public Terms() {
        this.terms = new ArrayList<Term>();
    }

    public void addTerm(Term term) {
        terms.add(term);
    }

    public Term getTerm(int index) {
        return terms.get(index);
    }

    public int count() {
        return terms.size();
    }

}
