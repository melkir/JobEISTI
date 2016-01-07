package com.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipient {
    @Id
    String name;
    List<String> mailingList = new ArrayList<>();
    SimpleDateFormat creationDate = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat editDate = new SimpleDateFormat("dd/MM/yyyy");
    @ManyToOne
    Newsletter newsletter;

    public Recipient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMailingList() {
        return mailingList;
    }

    public void setMailingList(List<String> mailingList) {
        this.mailingList = mailingList;
    }

    public Newsletter getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(Newsletter newsletter) {
        this.newsletter = newsletter;
    }

    public SimpleDateFormat getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(SimpleDateFormat creationDate) {
        this.creationDate = creationDate;
    }

    public SimpleDateFormat getEditDate() {
        return editDate;
    }

    public void setEditDate(SimpleDateFormat editDate) {
        this.editDate = editDate;
    }
}
