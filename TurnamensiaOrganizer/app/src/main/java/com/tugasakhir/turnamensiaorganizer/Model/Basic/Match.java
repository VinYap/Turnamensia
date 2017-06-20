package com.tugasakhir.turnamensiaorganizer.Model.Basic;

/**
 * Created by Asus on 18/06/2017.
 */

public class Match {
    private String matchRound;
    private String matchDate;
    private String radiant;
    private String dire;
    private Integer radiantPhotoId;
    private Integer direPhotoId;
    private Integer qrCodeId;

    public String getMatchRound() {
        return matchRound;
    }

    public void setMatchRound(String matchRound) {
        this.matchRound = matchRound;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getRadiant() {
        return radiant;
    }

    public void setRadiant(String radiant) {
        this.radiant = radiant;
    }

    public String getDire() {
        return dire;
    }

    public void setDire(String dire) {
        this.dire = dire;
    }

    public Integer getRadiantPhotoId() {
        return radiantPhotoId;
    }

    public void setRadiantPhotoId(Integer radiantPhotoId) {
        this.radiantPhotoId = radiantPhotoId;
    }

    public Integer getDirePhotoId() {
        return direPhotoId;
    }

    public void setDirePhotoId(Integer direPhotoId) {
        this.direPhotoId = direPhotoId;
    }

    public Integer getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(Integer qrCodeId) {
        this.qrCodeId = qrCodeId;
    }
}
