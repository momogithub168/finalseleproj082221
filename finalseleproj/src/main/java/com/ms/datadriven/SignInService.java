/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ms.datadriven;

import com.itexps.mavendatadriven.vo.SignInVO;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Mo Wan
 */
public class SignInService {

    //only load data once
    public static SignInVO loadSignInDetails() {
        SignInVO signIn = null;
        try {
            FileInputStream inputStream = new FileInputStream(new File("c:\\data\\talbots.xlsx"));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Row r = firstSheet.getRow(0);
            Cell c = r.getCell(1); //username value
            String username = c.getStringCellValue();

            r = firstSheet.getRow(1);
            c = r.getCell(1); //password value
            String password = c.getStringCellValue();

            signIn = new SignInVO(username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return signIn;
    }

    public boolean validate(String user, String password) {
        boolean status = Boolean.FALSE;
        SignInVO signin = SignInService.loadSignInDetails();
        if(signin.getUsername().equals(user) && signin.getPassword().equals(password)) {
            status = Boolean.TRUE;
        }
        return status;
    }

    public static void main(String args[]) {
        SignInService service = new SignInService();
        System.out.println(service.validate("u", "p"));
        System.out.println(service.validate("abc@abc.com", "abc123"));
    }
}
