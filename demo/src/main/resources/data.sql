-- Insertar aerolíneas
INSERT INTO aerolineas (id, nombre) VALUES (1, 'AeroTest');
INSERT INTO aerolineas (id, nombre) VALUES (2, 'SkyFlights');
INSERT INTO aerolineas (id, nombre) VALUES (3, 'FlyHigh');

-- Insertar vuelos
INSERT INTO vuelos (id, numero_vuelo, origen, destino) VALUES
                                                           (1, '550e8400-e29b-41d4-a716-446655440000', 'Londres', 'Madrid'),
                                                           (2, '660e8500-e29b-41d4-a716-446655440001', 'Roma', 'París'),
                                                           (3, '770e8600-e29b-41d4-a716-446655440002', 'Berlín', 'Ámsterdam');

-- Relacionar aerolíneas con vuelos
INSERT INTO aerolineas_vuelos (aerolinea_id, vuelo_id) VALUES (1, 1);
INSERT INTO aerolineas_vuelos (aerolinea_id, vuelo_id) VALUES (2, 2);
INSERT INTO aerolineas_vuelos (aerolinea_id, vuelo_id) VALUES (3, 3);

-- Insertar pasaportes
INSERT INTO pasaportes (id, numero) VALUES (1, 'ABC123456');
INSERT INTO pasaportes (id, numero) VALUES (2, 'XYZ987654');
INSERT INTO pasaportes (id, numero) VALUES (3, 'LMN567890');

-- Insertar pasajeros

INSERT INTO pasajeros (id, nombre, NID, pasaporte_id) VALUES
                                                          (1, 'Juan Pérez', 'NID001', 1),
                                                          (2, 'María López', 'NID002', 2),
                                                          (3, 'Carlos García', 'NID003', 3);

-- Insertar reservas
INSERT INTO reservas (id, codigo_de_reserva, pasajero_id, vuelo_id) VALUES
                                                                        (1, '880e8700-e29b-41d4-a716-446655440003', 1, 1),
                                                                        (2, '990e8800-e29b-41d4-a716-446655440004', 2, 2),
                                                                        (3, 'AA0e8900-e29b-41d4-a716-446655440005', 3, 3);
