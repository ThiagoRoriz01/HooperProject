package hooper.api.domain.hooper;

public record HooperDetailingData(Long id, String name, String email, String phone, String courtPosition) {

    public HooperDetailingData(HooperJPAEntity hooper) {
        this(hooper.getId(), hooper.getName(), hooper.getEmail(), hooper.getPhone(), hooper.getCourtPosition());
    }
}
