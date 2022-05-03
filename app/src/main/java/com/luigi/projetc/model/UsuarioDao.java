package com.luigi.projetc.model;

public class UsuarioDao {
    public  void CadastrarUsr(Usuario usuario){

    }

    public  Usuario atulizaUsuario(Usuario usuario){
        return new Usuario();
    }

    public Usuario login(Usuario usr ) {
        //Acessar o banco e realizar uma consulta SQL com login e senha

        //Caso tenha algum resultado verifica a senha e login, desnecessário caso já tenha realizado no banco.
        if (      1==1 /* Verifica login e senha  */)
            return new Usuario();
        return  null;
    }
}
