package com.welcomeToJeju.moj.web;

import java.beans.PropertyEditorSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.User;

@ControllerAdvice
public class GlobalControllerAdvice {

  // 프로퍼티 에디터를 등록하는 @InitBinder 메서드 정의
  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(User.class, new UserPropertyEditor());

    binder.registerCustomEditor(Category.class, new CategoryPropertyEditor());

    DatePropertyEditor propEditor = new DatePropertyEditor();
    binder.registerCustomEditor(java.util.Date.class, propEditor);
  }

  class UserPropertyEditor extends PropertyEditorSupport {
  }

  class CategoryPropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      Category category = new Category();
    }
  }

  class DatePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
      try {
        // String ==> java.util.Date
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // Date date = format.parse(text);
        // setValue(date);

        // String ==> java.sql.Date
        setValue(java.sql.Date.valueOf(text));

      } catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }
  }

  //  class CarPropertyEditor extends PropertyEditorSupport {
  //    @Override
  //    public void setAsText(String text) throws IllegalArgumentException {
  //      String[] values = text.split(",");
  //
  //      Car car = new Car();
  //      car.setModel(values[0]);
  //      car.setCapacity(Integer.parseInt(values[1]));
  //      car.setAuto(Boolean.parseBoolean(values[2]));
  //      car.setCreatedDate(java.sql.Date.valueOf(values[3]));
  //
  //      setValue(car);
  //    }
  //  }
  //
  //  class EnginePropertyEditor extends PropertyEditorSupport {
  //    @Override
  //    public void setAsText(String text) throws IllegalArgumentException {
  //      String[] values = text.split(",");
  //
  //      Engine engine = new Engine();
  //      engine.setModel(values[0]);
  //      engine.setCc(Integer.parseInt(values[1]));
  //      engine.setValve(Integer.parseInt(values[1]));
  //
  //      setValue(engine);
  //    }
  //  }


}
