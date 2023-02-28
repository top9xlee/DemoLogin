package com.Website.Step2.repository.roleRepository;

import com.Website.Step2.model.Role;
import lombok.RequiredArgsConstructor;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleRepositoryCustomImpl implements RoleRepositoryCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> getHighestRole(Long id){
        List<Role> res = null;
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT  r.role_name as roleName , MIN(a.role_id) AS roleId FROM user_role AS a");
        sql.append(" JOIN role AS r ON a.role_id = r.role_id  ");
        sql.append("WHERE a.user_id = " + id);

        res = entityManager.unwrap(org.hibernate.Session.class).createNativeQuery(sql.toString())
                .addScalar("roleId", LongType.INSTANCE)
                .addScalar("roleName", StringType.INSTANCE)

                .setResultTransformer(Transformers.aliasToBean(Role.class))
                .list();
        entityManager.close();
        return res;
    };

    @Override
    public List<String> getListRoleByUsername(String username){
        List<String> res = null;
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT r.role_name FROM user_role AS u");
        sql.append("JOIN role r ON u.role_id = r.role_id ");
        sql.append("JOIN user AS a ON u.user_id = a.user_id ");
        sql.append("WHERE a.username = "  + username+ " ");
        res = entityManager.unwrap(org.hibernate.Session.class).createNativeQuery(sql.toString())
                .addScalar("roleName", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(String.class))
                .list();
        entityManager.close();
        return res;
    }
    @Override
    public List<String> getListRoleByUserId(Long id){
        List<String> res = null;
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT r.role_name FROM user_role AS u");
        sql.append(" JOIN role r ON u.role_id = r.role_id  ");
        sql.append(" WHERE u.user_id = " + id);
        Query query = entityManager.createNativeQuery(sql.toString());
        res = query.getResultList();
        entityManager.close();
        return res;
    }
}
