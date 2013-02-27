/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package realsquad.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import realsquad.util.Position;

/**
 *
 * @author Tomek
 */
@Entity
@Table(name = "REAL_SQUAD_PLAYERS")
public class Player implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    Position primaryPosition;
    Collection <Position> alternativePosition;
    String name;
    String surname;

    public Player() {
    }
    
    public Player(String name, String surname, Position pos, String id){
        this.name = name;
        this.surname = surname;
        this.primaryPosition  = pos;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Position getPrimaryPosition(){
        return primaryPosition;
    }
    public void setPrimaryPosition(Position pos){
        this.primaryPosition = pos;
    }
     public String getName(){
        return name;
    }
    public void setName(String n){
        this.name = n;
    }
     public String getSurname(){
        return surname;
    }
    public void setSurname (String sn){
        this.surname = sn;
    }
}
