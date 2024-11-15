package br.com.api_eco_feira.service.central;

import br.com.api_eco_feira.dto.quantidade.QuantidadeAtendidaResponseList;
import br.com.api_eco_feira.enumerador.StatusDemanda;
import br.com.api_eco_feira.model.central.Demanda;
import br.com.api_eco_feira.model.central.DemandaQuantidadeAssociaProdutor;
import br.com.api_eco_feira.model.central.Demanda_Produto_Associados;
import br.com.api_eco_feira.repository.central.DemandaAssociaProdutorRepository;
import br.com.api_eco_feira.repository.central.DemandaQuantidadeAssociaProdutorRepository;
import br.com.api_eco_feira.repository.central.DemandaRepository;
import br.com.api_eco_feira.repository.central.Demanda_Produto_Associados_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DemandaQuantidadeAssociaProdutorService {

    @Autowired
    private DemandaQuantidadeAssociaProdutorRepository demandaQuantidadeAssociaProdutorRepository;

    @Autowired
    private DemandaRepository demandaRepository;

    @Autowired
    private Demanda_Produto_Associados_Repository demanda_produto_associados_repository;

    public String atendendo (DemandaQuantidadeAssociaProdutor demanda) {
        try{
            demandaQuantidadeAssociaProdutorRepository.save(demanda);
            return "Sucesso";
        }
        catch(Exception e){
            return "Erro";
        }
    }

    public List<QuantidadeAtendidaResponseList> getAtendimento(Long idDemanda, Long idProdutosAssociados){
        List<DemandaQuantidadeAssociaProdutor> demandaQuantidadeAssociaProdutor = demandaQuantidadeAssociaProdutorRepository.findAllByDemanda_IdDemandaAndAndDemandaProdutoAssociados_IdDemandaProduto(idDemanda, idProdutosAssociados);
        return demandaQuantidadeAssociaProdutor.stream().map(quantidadeAtendidaResponse -> {
            QuantidadeAtendidaResponseList quantidadeAtendidaResponseList = new QuantidadeAtendidaResponseList();
            quantidadeAtendidaResponseList.setId(quantidadeAtendidaResponse.getId());
            quantidadeAtendidaResponseList.setQuantidade(quantidadeAtendidaResponse.getQuantidade());
            quantidadeAtendidaResponseList.setProdutor(quantidadeAtendidaResponse.getEmpresa().getRazaoSocial());
            quantidadeAtendidaResponseList.setCnpj(quantidadeAtendidaResponse.getEmpresa().getCnpj());
            return quantidadeAtendidaResponseList;
        }).collect(Collectors.toList());

    }

    public String delete(Long idQuantidadeAtendida, Long idDemanda, double quantidade) {
        try {
            // Verifica se a Quantidade Atendida existe
            DemandaQuantidadeAssociaProdutor demandaQuantidadeAssociaProdutor = demandaQuantidadeAssociaProdutorRepository.findById(idQuantidadeAtendida).orElse(null);
            if (demandaQuantidadeAssociaProdutor == null) {
                return "Erro: Quantidade Atendida não encontrada";
            }

            // Verifica se o Produto Associado existe
            Long idProdutoAssociado = demandaQuantidadeAssociaProdutor.getDemandaProdutoAssociados().getIdDemandaProduto();
            Demanda_Produto_Associados demandaProdutoAssociados = demanda_produto_associados_repository.findById(idProdutoAssociado).orElse(null);
            if (demandaProdutoAssociados == null) {
                return "Erro: Produto Associado não encontrado";
            }

            double saldoAtual = demandaProdutoAssociados.getSaldo();
            System.out.println("Saldo atual: " + saldoAtual);

            // Atualiza a quantidade
            double saldoNovo = saldoAtual - quantidade;
            System.out.println("Quantidade Nova: " + saldoNovo);

            // Atualiza o campo 'saldo' no banco de dados
            demandaProdutoAssociados.setSaldo(saldoNovo);
            demanda_produto_associados_repository.save(demandaProdutoAssociados);

            // Verifica se o valor foi salvo corretamente
            System.out.println("Quantidade Salva no BD: " + demandaProdutoAssociados.getSaldo()); // Use getSaldo() ao invés de getQuantidade()

            // Deleta a Quantidade Atendida
            demandaQuantidadeAssociaProdutorRepository.delete(demandaQuantidadeAssociaProdutor);

            // Verifica se a Demanda existe
            Demanda demanda = demandaRepository.findById(idDemanda).orElse(null);
            if (demanda == null) {
                System.out.println("Erro: Demanda com ID " + idDemanda + " não encontrada.");
                return "Erro: Demanda não encontrada";
            }

            System.out.println("Status Atual da Demanda: " + demanda.getStatusDemanda());

            // Atualiza o status da Demanda
            demanda.setStatusDemanda(StatusDemanda.ABERTA);
            demandaRepository.save(demanda);

            System.out.println("Novo Status da Demanda: " + demanda.getStatusDemanda());

            return "Sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro";
        }
    }



}
