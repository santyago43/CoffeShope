# Coffee Roastery - Arquitectura Hexagonal

Este proyecto muestra un ejemplo de arquitectura hexagonal (puertos y adaptadores) aplicado a una tostaduría de café.

## Preguntas basadas en la experiencia de desarrollo

1. **Si el día de mañana la tostaduría decide cambiar la base de datos en memoria por PostgreSQL, ¿qué carpetas o clases de tu proyecto tendrías que modificar y cuáles se mantendrían intactas?**

   Solo tendría que crear una nueva implementación de los puertos de salida (InventoryPort y OrderRepositoryPort) que use PostgreSQL y actualizar la configuración de Spring. Las carpetas y clases que permanecen intactas son:
   - El dominio 
   - Los casos de uso
   - Los puertos de entrada y salida
   - Los adaptadores primarios
   - La configuración de la aplicación (ApplicationConfig.java)

2. **¿Por qué es importante que el ProcessCoffeeOrderUseCase no conozca la existencia del InMemoryInventoryAdapter?**

   Es importante porque el principio de separación de capas y la inversión de dependencias requieren que el caso de uso  dependa solo de las interfaces, no de implementaciones concretas. Si el caso de uso conociera InMemoryInventoryAdapter, estaría acoplado a una tecnología específica y no podría funcionar con otra forma de almacenamiento sin cambiar su código. Al depender de la interfaz InventoryPort, el caso de uso permanece agnóstico de cómo se obtiene o actualiza el inventario, lo que hace que el negocio sea más flexible, testeable y independiente de detalles de infraestructura.
