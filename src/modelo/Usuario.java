/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author CristianCamiloAlvare
 */
public class Usuario {
    int id;
    String name;
    String mail;
    String tel;
    String rol;
   
    public Usuario (int id,String name,String mail, String tel, String rol){
    this.id= id;
    this.name = name;
    this.mail = mail;
    this.tel = tel;
    this.rol= rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }
 public Usuario (){
}
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

  
    
    
}

