/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlgames.controller;

import br.com.controlgames.dao.GenericDAO;
import br.com.controlgames.dao.VendedorDAO;
import br.com.controlgames.model.Vendedor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wesle
 */
@WebServlet(name = "VendedorAlterar", urlPatterns = {"/VendedorAlterar"})
public class VendedorAlterar extends HttpServlet {

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
        int idVendedor = Integer.parseInt(request.getParameter("idVendedor"));
        String nome = request.getParameter("nomePessoa");
        String url = request.getParameter("url");
        String observacao = request.getParameter("observacao");
        String cpf = request.getParameter("cpfCnpjPessoa");
        String mensagem = null;
        
        //limpa cof cnpj
        cpf = cpf.replaceAll("[./-]", "");
        
        Vendedor oVendedor = new Vendedor (idVendedor, url, observacao, "A", idVendedor, cpf, nome);
        
        try{
            GenericDAO dao = new VendedorDAO();
            if(dao.alterar(oVendedor)){
                mensagem = "Vendedor alterado com Sucesso!";
            }else{
                mensagem = "Problemas ao cadastrar Vendedor. Verifique os dados informados e tente novamente!";
            }
            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("VendedorListar").forward(request, response);
        }catch(Exception ex){
            System.out.println("Problemas no Servlet ao alterar Vendedor! Erro: "+ex.getMessage());
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
