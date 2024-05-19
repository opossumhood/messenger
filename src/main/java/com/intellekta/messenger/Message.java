package com.intellekta.messenger;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_id_seq")
    @SequenceGenerator(name = "sales_id_seq", sequenceName = "sales_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "user")
    private String user;

    @Column(name = "text")
    private String text;

    public Message() {
    }

    public Message(String user, String text) {
        this.user = user;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
