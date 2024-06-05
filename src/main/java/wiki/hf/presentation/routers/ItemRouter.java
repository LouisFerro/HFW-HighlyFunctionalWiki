package wiki.hf.presentation.routers;

import wiki.hf.domain.*;
import wiki.hf.service.*;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.*;
import lombok.extern.log4j.*;

import java.util.*;

@RequiredArgsConstructor

@Log4j2
@Controller
@RequestMapping("/page")
public class ItemRouter {

    private final ItemService itemService;

    @GetMapping("/{name}")
    private String browsePage(Model model, @PathVariable String name) {
        log.info("Loading page '{}'", name);

        Optional<Item> page = itemService.findPageByName(name);

        if (page.isEmpty()) {
            log.info("Page '{}' doesn't exist", page);
            return "page/404";
        }

        List<Item> items = itemService.findItemsByPage(page.get());

        model.addAttribute("page", page.get());
        model.addAttribute("items", items);

        log.info("Loading of page '{}' completed", name);

        return "page/main";
    }
}