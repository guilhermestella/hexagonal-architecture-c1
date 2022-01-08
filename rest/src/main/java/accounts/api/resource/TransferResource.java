package accounts.api.resource;

import accounts.application.ports.in.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import accounts.api.dto.TransferForm;

@RestController
@RequestMapping("/transfers")
public class TransferResource {

    private final TransferService transferService;

    public TransferResource(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody TransferForm form) {
        try {
            transferService.transfer(form.fromAccount(), form.toAccount(), form.amount());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
