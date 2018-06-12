/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.DomeinController;

import ui.Applicatie_UC1;

/**
 *
 * @author KriNi
 */
public class StartUp
{
    public static void main(String[] args)
    {
        DomeinController dc = new DomeinController();
        Applicatie_UC1 app = new Applicatie_UC1(dc);
           app.startOp(); 
        //hallo robin 2
 

    }
}