package com.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Newsletter extends AbstractDocument {
    @OneToMany(mappedBy = "newsletter")
    private Set<Recipient> recipients;

    public Newsletter() {
    }

    public Set<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<Recipient> recipients) {
        this.recipients = recipients;
    }
}
