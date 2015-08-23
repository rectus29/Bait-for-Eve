package com.rectuscorp.evetool.dao;


import com.rectuscorp.evetool.entities.Role;
import com.rectuscorp.evetool.entities.User;

import java.util.List;


/*-----------------------------------------------------*/
/*           _/_/    _/      _/  _/_/_/    _/_/_/  _/  */
/*        _/    _/  _/_/    _/  _/    _/    _/    _/   */
/*      _/_/_/_/  _/  _/  _/  _/    _/    _/    _/     */
/*    _/    _/  _/    _/_/  _/    _/    _/    _/       */
/*  _/    _/  _/      _/  _/_/_/    _/_/_/  _/_/_/_/   */
/*                                                     */
/* User: Rectus for          Date: 21/12/12 11:22 */
/*                                                     */
/*                 All right reserved                  */
/*-----------------------------------------------------*/

public interface IdaoRole {

    public Role getRoleByName(String roleName);

    public List<Role> getAuthorizedRole(User u);


}
