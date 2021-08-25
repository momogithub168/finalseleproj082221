/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ms.datadriven;


import com.ms.datadriven.vo.SignInVO;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
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
    public static List<SignInVO> loadSignInDetails() {
        List<SignInVO> list = new ArrayList<>();
        
        try {
            FileInputStream inputStream = new FileInputStream(new File("c:\\data\\talbots.xlsx"));
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);

            for(int i =1; i <= firstSheet.getLastRowNum(); i++) {
                Row r = firstSheet.getRow(i);
                Cell c = r.getCell(0); //id value
                int id = (int)c.getNumericCellValue();
                c = r.getCell(1);   //username value
                String username = c!=null?c.getStringCellValue():"";
                c = r.getCell(2); //password value
                String password = c!=null?c.getStringCellValue():"";        
                SignInVO signIn = new SignInVO(id, username, password);
                list.add(signIn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean validate(String user, String password) {
        boolean status = Boolean.FALSE;
        List<SignInVO> list = SignInService.loadSignInDetails();
        list.stream().forEach(s->System.out.println(s));
        SignInVO signin = list.get(0);
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
