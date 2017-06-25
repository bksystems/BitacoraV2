package com.apps.movilidadcb.bitacorav2.Entitys;

import android.app.Application;

/**
 * Created by jcarl on 05/11/2016.
 */

public class UserGlobalClass extends Application{
    private UsuarioEntity usuario;
    private boolean isLoggin;

    public UsuarioEntity getUserEntity() {
        return usuario;
    }

    public void setUserEntity(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public boolean isLoggin() {
        return isLoggin;
    }

    public void setIsLoggin(boolean isLoggin) {
        this.isLoggin = isLoggin;
    }

}
