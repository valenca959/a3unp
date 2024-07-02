
package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeradorDeDados {
    // Gerar um CPF aleatório
   
    public static String gerarCpf() {
        String cpf = null;
        try {
            URL url = new URL("https://api.invertexto.com/v1/faker?token=8106|FgwY7sEGTJZmNB76ibHOzN8orHGcGdeq&fields=name,cpf&locale=pt_BR");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            String response = content.toString();
            
            String[] parts = response.split("cpf\":\"");

            cpf = parts[1].substring(0, 14);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        return cpf;
        
    }

    //Gerar um nome aleatório
    public static String gerarNome() {
        String nome = null;
        try {
            URL url = new URL("https://api.invertexto.com/v1/faker?token=8106|FgwY7sEGTJZmNB76ibHOzN8orHGcGdeq&fields=name&locale=pt_BR");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            String response = content.toString();

            String[] parts = response.split("name\":\"");

            nome = parts[1].substring(0, parts[1].indexOf("\""));

            // tratar o nome para não ficar como o exemplo Cort\u00eas
            nome = nome.replace("\\u00e1", "á");
            nome = nome.replace("\\u00e3", "ã");
            nome = nome.replace("\\u00e7", "ç");
            nome = nome.replace("\\u00e9", "é");
            nome = nome.replace("\\u00ed", "í");
            nome = nome.replace("\\u00f3", "ó");
            nome = nome.replace("\\u00fa", "ú");
            nome = nome.replace("\\u00e0", "à");
            nome = nome.replace("\\u00e8", "è");
            nome = nome.replace("\\u00ec", "ì");
            nome = nome.replace("\\u00f2", "ò");
            nome = nome.replace("\\u00f9", "ù");
            nome = nome.replace("\\u00ea", "ê");
            nome = nome.replace("\\u00ee", "î");
            nome = nome.replace("\\u00f4", "ô");
            nome = nome.replace("\\u00fb", "û");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nome;
    }

    //Gerar um email aleatório
    public static String gerarEmail() {
        String email = null;
        try {
            URL url = new URL("https://api.invertexto.com/v1/faker?token=8106|FgwY7sEGTJZmNB76ibHOzN8orHGcGdeq&fields=email&locale=pt_BR");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            String response = content.toString();

            String[] parts = response.split("email\":\"");

            email = parts[1].substring(0, parts[1].indexOf("\""));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }

    //Gerar um data de nascimento aleatória
    public static String gerarDataNascimento() {
        String dataNascimento = null;
        try {
            URL url = new URL("https://api.invertexto.com/v1/faker?token=8106|FgwY7sEGTJZmNB76ibHOzN8orHGcGdeq&fields=birth_date&locale=pt_BR");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            String response = content.toString();

            String[] parts = response.split("birth_date\":\"");

            dataNascimento = parts[1].substring(0, parts[1].indexOf("\""));

            //converte a data para o formato dd/MM/yyyy
            String[] data = dataNascimento.split("-");
            dataNascimento = data[2] + "/" + data[1] + "/" + data[0];

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataNascimento;
    }


}


