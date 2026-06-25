import controller.OrdemServicoController;
import model.Cliente;
import model.OrdemServico;
import model.Veiculo;
import repository.ClienteRepository;
import repository.OrdemServicoRepository;
import repository.VeiculoRepository;
import service.OrdemServicoService;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        ClienteRepository clienteRepository = new ClienteRepository();
        VeiculoRepository veiculoRepository = new VeiculoRepository();
        OrdemServicoRepository osRepository = new OrdemServicoRepository();
        OrdemServicoService osService       = new OrdemServicoService( osRepository, veiculoRepository);
        OrdemServicoController osController = new OrdemServicoController( osService );

        try {
            Cliente cliente = new Cliente("João Silva", "44999999999");
            clienteRepository.salvar(cliente); // aqui ele faz insert

            Veiculo veiculo = new Veiculo("DEV1H05", "Gol",2001, 1L);
            veiculoRepository.salvar(veiculo);

            OrdemServico os = new OrdemServico(1L, "Troca de óleo", new BigDecimal("150.00"), "ABERTA");
            osController.abrirOrdem(os);

            System.out.println("Cliente, veiculo e ordem cadastrado");

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}