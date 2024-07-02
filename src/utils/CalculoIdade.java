package utils;

public class CalculoIdade {
    public static int calcularIdade(String dataNascimento) {
        String[] data = dataNascimento.split("/");
        int dia = Integer.parseInt(data[0]);
        int mes = Integer.parseInt(data[1]);
        int ano = Integer.parseInt(data[2]);
        int idade = 0;

        // Pega data atual
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int anoAtual = cal.get(java.util.Calendar.YEAR);
        int mesAtual = cal.get(java.util.Calendar.MONTH) + 1; // Janeiro é 0
        int diaAtual = cal.get(java.util.Calendar.DAY_OF_MONTH);

        // Calcula idade
        idade = anoAtual - ano;
        if (mesAtual < mes || (mesAtual == mes && diaAtual < dia)) {
            idade--;
        }

        return idade;
    }

    public static boolean validarIdade(String dataNascimento) {
        int idade = calcularIdade(dataNascimento);
        return idade >= 13 && idade <= 120;
    }
}

