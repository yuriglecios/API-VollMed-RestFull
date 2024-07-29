package com.api.med.medico;

public record DadosRetornoMedicoDto(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DadosRetornoMedicoDto(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
