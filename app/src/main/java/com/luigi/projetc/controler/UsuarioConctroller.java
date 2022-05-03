package com.luigi.projetc.controler;

import com.luigi.projetc.model.Usuario;
import com.luigi.projetc.model.UsuarioDao;

public class UsuarioConctroller {
    UsuarioDao usuarioDao;

    public UsuarioConctroller() {
        this.usuarioDao = new UsuarioDao();
    }
    /*cadastrar , alterar, excluir e login   */


    public void cadastrarUsuario(Usuario  u){
        if (u.getNome().length()>=3 ) {
            this.usuarioDao.CadastrarUsr(u);
        }
    }

    public Usuario alteraUsuario(Usuario u){
        //verificações de negocio
        return  this.usuarioDao.atulizaUsuario(u);
    }




}
