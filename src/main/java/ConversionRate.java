import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class ConversionRate {
    private final LocalDate conversionDate;
    private final Double rate;
}
