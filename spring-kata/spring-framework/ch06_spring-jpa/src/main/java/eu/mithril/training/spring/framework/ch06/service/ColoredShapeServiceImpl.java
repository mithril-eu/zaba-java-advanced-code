package eu.mithril.training.spring.framework.ch06.service;

import eu.mithril.training.spring.framework.ch06.dao.ColoredShapeDao;
import eu.mithril.training.spring.framework.ch06.model.ColoredShape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ColoredShapeServiceImpl implements ColoredShapeService {

    @Autowired
    ColoredShapeDao coloredShapeDao;

    @Override
    public ColoredShape getColoredShape(final Long id) {
        return coloredShapeDao.findById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void addColoredShape(final ColoredShape coloredShape) {
        coloredShapeDao.save(coloredShape);
    }
}
