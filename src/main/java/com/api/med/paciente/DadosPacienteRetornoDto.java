package com.api.med.paciente;

public record DadosPacienteRetornoDto(
        String nome,
        String email,
        String cpf
) {
    public DadosPacienteRetornoDto(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
