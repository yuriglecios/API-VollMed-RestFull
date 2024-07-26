package com.api.med.medico;

public record DadosRetornoMedicoDto(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DadosRetornoMedicoDto(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
