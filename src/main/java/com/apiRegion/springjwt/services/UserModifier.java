package com.apiRegion.springjwt.services;

import com.apiRegion.springjwt.Message.ReponseMessage;
import com.apiRegion.springjwt.models.Produit;
import com.apiRegion.springjwt.models.User;

public interface UserModifier {

    ReponseMessage Modifier(User user, Long id);
    ReponseMessage ModifierAvatar(User user,Long id);

    public void resetPassword(User user);

    public void updateUserPassword(User user, String newPassword);

    User findByEmail(String userEmail);
}
