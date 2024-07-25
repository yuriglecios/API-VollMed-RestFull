package com.api.med.paciente;

import com.api.med.endereco.EnderecoDto;

public record DadosCadastroPacienteDTO(
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoDto endereco
) {
}
