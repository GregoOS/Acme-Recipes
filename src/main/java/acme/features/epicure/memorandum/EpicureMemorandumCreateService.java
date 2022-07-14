package acme.features.epicure.memorandum;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.finedish.FineDish;
import acme.entities.memorandum.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumCreateService implements AbstractCreateService<Epicure, Memorandum> {

    @Autowired
    EpicureMemorandumRepository repository;

    @Override
    public boolean authorise(final Request<Memorandum> request) {
        assert request != null;

        final int epicureId = request.getPrincipal().getActiveRoleId();
        final int fineDishId = request.getModel().getInteger("masterId");
        final FineDish fineDish = this.repository.findOneFineDishById(fineDishId);
        final int fineDishEpicureId = fineDish.getEpicure().getId();

        return epicureId == fineDishEpicureId && !fineDish.isDraft();
    }

    @Override
    public void bind(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        request.bind(entity, errors, "request", "link");

        Integer masterId;
        FineDish fineDish;

        masterId = request.getModel().getInteger("masterId");
        fineDish = this.repository.findOneFineDishById(masterId);
        entity.setFineDish(fineDish);
    }

    @Override
    public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
        assert request != null;
        assert entity != null;
        assert model != null;

        request.unbind(entity, model, "sequenceNumber", "report", "link");

        Integer masterId;

        masterId = request.getModel().getInteger("masterId");

        model.setAttribute("masterId", masterId);
    }

    @Override
    public Memorandum instantiate(final Request<Memorandum> request) {
        assert request != null;

        Memorandum result;
        Integer masterId;
        FineDish fineDish;
        int numberOfReports;
        String sequenceNumber;
        Date creationTime;

        masterId = request.getModel().getInteger("masterId");
        fineDish = this.repository.findOneFineDishById(masterId);
        numberOfReports = this.repository.countMemorandumsInFineDishById(fineDish.getId());
        sequenceNumber = fineDish.getCode() + String.format(":%04d", numberOfReports + 1);
        creationTime = new Date(System.currentTimeMillis() - 1);

        result = new Memorandum();
        result.setCreationTime(creationTime);
        result.setSequenceNumber(sequenceNumber);
        result.setFineDish(fineDish);

        return result;
    }

    @Override
    public void validate(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
        assert request != null;
        assert entity != null;
        assert errors != null;

        boolean confirmation;

        confirmation = request.getModel().getBoolean("confirmation");
        errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
    }

    @Override
    public void create(final Request<Memorandum> request, final Memorandum entity) {
        assert request != null;
        assert entity != null;

        final Date creationTime = new Date(System.currentTimeMillis() - 1);
        entity.setCreationTime(creationTime);

        this.repository.save(entity);
    }
}
