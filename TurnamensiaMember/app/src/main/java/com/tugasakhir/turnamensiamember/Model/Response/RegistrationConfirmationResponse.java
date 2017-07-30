package com.tugasakhir.turnamensiamember.Model.Response;

import com.tugasakhir.turnamensiamember.Model.Basic.Bank;
import com.tugasakhir.turnamensiamember.Model.Basic.Member;
import com.tugasakhir.turnamensiamember.Model.Basic.RegistrationConfirmation;
import com.tugasakhir.turnamensiamember.Model.Basic.Response;
import com.tugasakhir.turnamensiamember.Model.Basic.Team;
import com.tugasakhir.turnamensiamember.Model.Basic.Tournament;
import com.tugasakhir.turnamensiamember.Model.Basic.TournamentRegistration;

import java.util.List;

/**
 * Created by Asus on 30/07/2017.
 */

public class RegistrationConfirmationResponse extends Response {
    private List<Bank> banks;
    private TournamentRegistration tournament_registration;
    private Tournament tournament;
    private Team team;
    private List<Member> members;
    private RegistrationConfirmation tournament_registration_confirmation;

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public TournamentRegistration getTournament_registration() {
        return tournament_registration;
    }

    public void setTournament_registration(TournamentRegistration tournament_registration) {
        this.tournament_registration = tournament_registration;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public RegistrationConfirmation getTournament_registration_confirmation() {
        return tournament_registration_confirmation;
    }

    public void setTournament_registration_confirmation(RegistrationConfirmation tournament_registration_confirmation) {
        this.tournament_registration_confirmation = tournament_registration_confirmation;
    }
}
