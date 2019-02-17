package br.com.infox.util;

import java.sql.SQLException;

public class Excessao {

    /**
     * Efetua o tratamento de exceções do banco de dados. O método está exibindo uma
     * mensagem Swing, porém isso é apenas uma idéia, o tratamento poderiase feito
     * de várias formas
     *
     * @param ex Uma instância de {@link Exception}
     * @return
     */
    public static String tratarDbException(Exception ex) {
        // Verifica nas causas das exceções se há um SQLException, limitando a no máximo
        // 5 interações no Looping
        Throwable tmpEx = ex;
        String menssagem;
        int num = 0;
        do {
            num++;
            // Se a causa é um SQLException exibe mensagem
            if (tmpEx instanceof SQLException) {
                SQLException sqlEx = (SQLException) tmpEx;
                menssagem = "Falha ao acessar banco de dados[CODIGO:" + sqlEx.getErrorCode() + ", ERRO:"
                        + sqlEx.getMessage() + "]";
                System.out.println(menssagem);
                return menssagem;
            }
            // Se ainda não é um SQLException continua a integração
            tmpEx = tmpEx.getCause();
        } while (tmpEx != null && num < 6);
        // Se chegou até aqui, indica que dentro das causas não foi encontrada um
        // SQLException, e faz um tratamento genérico, a partir da Exception principal
        menssagem = "Falha inesperada ao acessar o banco de dados[ERRO: " + ex.getMessage() + "]";
        System.out.println(menssagem);
        return menssagem;
    }

    public static void erro(Exception e) {
        e.printStackTrace();
    }

}
