package com.api.med.paciente;

public record DadosPacienteRetornoDto(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public DadosPacienteRetornoDto(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
