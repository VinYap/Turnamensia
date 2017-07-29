package com.tugasakhir.turnamensiamember.Model.Basic;

import java.util.List;

/**
 * Created by Andrianto on 7/28/2017.
 */

public class Dota2LiveMatchPlayer {
    private Long id;
    private String name;
    private Integer kills;
    private Integer death;
    private Integer assists;
    private Integer last_hits;
    private Integer denies;
    private Integer net_worth;
    private List<Dota2Items> items;
    private Member member;
    private Dota2Heroes hero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getLast_hits() {
        return last_hits;
    }

    public void setLast_hits(Integer last_hits) {
        this.last_hits = last_hits;
    }

    public Integer getDenies() {
        return denies;
    }

    public void setDenies(Integer denies) {
        this.denies = denies;
    }

    public Integer getNet_worth() {
        return net_worth;
    }

    public void setNet_worth(Integer net_worth) {
        this.net_worth = net_worth;
    }

    public List<Dota2Items> getItems() {
        return items;
    }

    public void setItems(List<Dota2Items> items) {
        this.items = items;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Dota2Heroes getHero() {
        return hero;
    }

    public void setHero(Dota2Heroes hero) {
        this.hero = hero;
    }
}
