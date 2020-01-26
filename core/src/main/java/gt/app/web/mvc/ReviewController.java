package gt.app.web.mvc;

import gt.app.domain.Note;
import gt.app.domain.NoteStatus;
import gt.app.modules.note.NoteReviewDto;
import gt.app.modules.note.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    final NoteService noteService;

    @GetMapping("/review/{id}")
    public String startEditNote(Model model, @PathVariable Long id) {
        model.addAttribute("note", noteService.readForReview(id));
        return "admin/review-note";
    }

    @PostMapping("/review")
    public String finishEditNote(NoteReviewDto reviewResult, RedirectAttributes redirectAttrs) {

        Optional<Note> noteOpt = noteService.handleReview(reviewResult);

        String action = reviewResult.getVerdict() == NoteStatus.PUBLISHED ? "Approved" : "Rejected";

        noteOpt.ifPresentOrElse(
            n -> redirectAttrs.addFlashAttribute("success", "Note with id " + reviewResult.getId() + " is " + action),
            () -> redirectAttrs.addFlashAttribute("success", "Note with id " + reviewResult.getId() + " is already reviewed or doesn't exists")
        );

        return "redirect:/admin/";
    }
}