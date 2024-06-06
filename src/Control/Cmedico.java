/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Conexao.Conexao;
import Modelo.Mmedico;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author TEODORO
 */
public class Cmedico {

    Conexao c = new Conexao();
    private PreparedStatement p;
    private ResultSet r;

    public void salvar(Mmedico m) {
        String sql = "insert into medico(nome,genero,telefone,bi,nif,morada,especialidade,usuario,senha)"
                + " values(?,?,?,?,?,?,?,?,?)";
        try {
            p = c.conexao().prepareStatement(sql);
            p.setString(1, m.getNome());
            p.setString(2, m.getGenero());
            p.setString(3, m.getTelefone());
            p.setString(4, m.getBi());
            p.setString(5, m.getNif());
            p.setString(6, m.getMorada());
            p.setString(7, m.getEspecialidade());
            p.setString(8, m.getUsuario());
            p.setString(9, m.getSenha());
            if (p.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "SUCESSO");
            } else {
                JOptionPane.showMessageDialog(null, "ERRO");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public void editar(Mmedico m) {
        String sql = "update medico set nome=?,genero=?,telefone=?,bi=?,nif=?"
                + ",morada=?,especialidade=?,usuario=?,senha=? where idm=?";
        try {
            p = c.conexao().prepareStatement(sql);
            p.setString(1, m.getNome());
            p.setString(2, m.getGenero());
            p.setString(3, m.getTelefone());
            p.setString(4, m.getBi());
            p.setString(5, m.getNif());
            p.setString(6, m.getMorada());
            p.setString(7, m.getEspecialidade());
            p.setString(8, m.getUsuario());
            p.setString(9, m.getSenha());
            p.setString(10, m.getCodigo());
            if (p.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "SUCESSO");
            } else {
                JOptionPane.showMessageDialog(null, "ERRO");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public ArrayList<Mmedico> pesquisar(String pesquisar) {
        ArrayList<Mmedico> recebe = new ArrayList<>();

        String sql = "select * from medico where nome like '%" + pesquisar + "%'order by idm";
        try {
            p = c.conexao().prepareStatement(sql);
            r = p.executeQuery();
            while (r.next()) {
                Mmedico m = new Mmedico();
                m.setBi(r.getString("bi"));
                m.setCodigo(r.getString("idm"));
                m.setEspecialidade(r.getString("especialidade"));
                m.setGenero(r.getString("genero"));
                m.setMorada(r.getString("morada"));
                m.setNif(r.getString("nif"));
                m.setNome(r.getString("nome"));
                m.setTelefone(r.getString("telefone"));
                m.setUsuario(r.getString("usuario"));
                m.setSenha(r.getString("senha"));
                recebe.add(m);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return recebe;
    }
}
