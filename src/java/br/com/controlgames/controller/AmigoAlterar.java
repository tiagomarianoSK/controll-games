/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.controller;

import br.com.controlgames.model.Amigo;
import br.com.controlgames.dao.AmigoDAO;
import br.com.controlgames.dao.GenericDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wesle
 */
@WebServlet(name = "AmigoAlterar", urlPatterns = {"/AmigoAlterar"})
public class AmigoAlterar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         int idAmigo = Integer.parseInt(request.getParameter("idAmigo"));
        String nome = request.getParameter("nomePessoa");
        String observacao = request.getParameter("observacao");
        String cpf = request.getParameter("cpfCnpjPessoa");
        String mensagem = null;
        
        //limpa cof cnpj
        cpf = cpf.replaceAll("[./-]", "");
        
        Amigo oAmigo = new Amigo(idAmigo, observacao, "A", idAmigo, cpf, nome);
        
        try{
            HttpSession sessao = request.getSession(false);
            if (sessao != null) {
            GenericDAO dao = new AmigoDAO();
            if(dao.alterar(oAmigo)){
                mensagem = "Amigo alterado com Sucesso!";
            }else{
                mensagem = "Problemas ao cadastrar amigo. Verifique os dados informados e tente novamente!";
            }
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("AmigoListar").forward(request, response);
             } else {
                request.setAttribute("mensagem", "amigo nao logado no sistema");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }catch(Exception ex){
            System.out.println("Problemas no Servlet ao alterar Amigo! Erro: "+ex.getMessage());
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
