package edu.itau.vaga.dtos;

/**
 * DTO para representar as estatísticas das transações.
 * Garante valores padrão quando não há transações.
 */
public record EstatisticasDto(
        long count,
        double sum,
        double avg,
        double min,
        double max) {
    public EstatisticasDto {
        if (count == 0) {
            min = 0.0;
            max = 0.0;
        }
    }
}
