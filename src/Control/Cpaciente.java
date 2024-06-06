/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Conexao.Conexao;
import Modelo.Mpaciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author TEODORO
 */
public class Cpaciente {

    Conexao c = new Conexao();
    private PreparedStatement ps;
    private ResultSet rs;

    public void guardar(Mpaciente p) {
        String sql = "insert into paciente(nome,idade,morada,telefone,estado,idmedico) values(?,?,?,?,?,1)";
        try {
            ps = c.conexao().prepareStatement(sql);
            ps.setString(1, p.getNome());
            ps.setString(2, p.getIdade());
            ps.setString(3, p.getMorada());
            ps.setString(4, p.getTelefone());
            ps.setString(5, p.getEstado());
            ps.setString(6, p.getMedico());

            if (ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "SUCESSO");
            } else {
                JOptionPane.showMessageDialog(null, "ERRO");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }

   
    public ArrayList<Mpaciente> pesquisar(String pesquisar) {
        ArrayList<Mpaciente> recebe = new ArrayList<>();

        String sql = "select * from paciente where nome like '%" + pesquisar + "%'order by idp";
        try {
            ps = c.conexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Mpaciente m = new Mpaciente();
                m.setCodigo(rs.getString("idp"));
                m.setMorada(rs.getString("morada"));
                m.setEstado(rs.getString("estado"));
                m.setMedico(rs.getString("idmedico"));
                m.setIdade(rs.getString("idade"));
                m.setNome(rs.getString("nome"));
                m.setTelefone(rs.getString("telefone"));
                recebe.add(m);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return recebe;
    }
}
