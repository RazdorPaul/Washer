.PHONY: build test check run help

# Очистка и сборка проекта
build:
	./gradlew clean build

# Запуск тестов
test:
	./gradlew test

# Проверка кода (checkstyle - добавим позже)
check:
	@echo "Checkstyle will be added later"

# Запуск приложения
run:
	./gradlew run

# Помощь
help:
	@echo "Доступные цели:"
	@echo "  make build    - Очистка и сборка проекта"
	@echo "  make test     - Запуск тестов"
	@echo "  make check    - Проверка кода (checkstyle)"
	@echo "  make run      - Запуск приложения"
	@echo "  make help     - Показать эту справку"