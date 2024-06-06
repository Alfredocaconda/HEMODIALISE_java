/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Conexao.Conexao;
import Modelo.Mconsulta;
import Modelo.Mmedico;
import Modelo.Mpaciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author TEODORO
 */
public class Cconsulta {

    private PreparedStatement ps;
    private ResultSet rs;
    Conexao c = new Conexao();

    public void salvar(Modelo.Mconsulta m) {
        String sql = "insert into consulta values(default,?,?,?,1)";
        try {
            ps = c.conexao().prepareStatement(sql);
//            ps.setString(1, m.getPaciente().getCodigo());
            ps.setString(1, m.getPaciente());
            ps.setString(2, m.getSintoma());
            ps.setString(3, m.getData());
//            ps.setString(4, m.getMedico());
//            ps.setString(4, m.getMedico().getCodigo());
            if (ps.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "SUCESSO");
            } else {
                JOptionPane.showMessageDialog(null, "ERRO");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public ArrayList<Mconsulta> pesquisar(String pesquisar) {
        //instanciamos o arraylist
        ArrayList<Mconsulta> dado = new ArrayList<>();
        //declaramos uma variavel qualquer que vai listar e pesquisar na tabela
//        String sql = "select p.nome,m.nome as nome,c.sintoma,c.data from consulta as c join paciente"
//                + " as p on c.idpaciente=p.idpaciente join medico as m on c.idmedico=m.idm "
//                + "where nome like '%" + pesquisar + "%' order by idc";
        String sql="select * from consulta where nomepa like '%"+pesquisar+"%' order by idc";
        try {
            ps = c.conexao().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //instanciamos a modal
                Mconsulta c = new Mconsulta();
//                Mpaciente p= new Mpaciente();
//                Mmedico m= new Mmedico();
                c.setPaciente(rs.getString("nomepa"));
//                c.setPaciente(p);
//                c.setMedico(rs.getString("idmedico"));
//                c.setMedico(m);
                c.setCodigo(rs.getString("idc"));
                c.setData(rs.getString("data"));
                c.setSintoma(rs.getString("sintoma"));
                dado.add(c);
            }
        } catch (Exception e) {
        }
        return dado;
    }
}
