package hooper.api.domain.hooper;

public record HooperDataList(Long id, String name, String courtPosition) {

    public HooperDataList(HooperJPAEntity hooper) {
        this(hooper.getId(), hooper.getName(), hooper.getCourtPosition());

        /*
        a partir do objeto hooper, ou seja, a partir de um objeto da nossa
        entidade JPA, os atributos name e courtPosition do nosso record/DTO
        HooperDataList são preenchidos
         */
    }
}
