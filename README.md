# Self-Watering System

This project is a **Self-Watering System** designed to automate watering for plants using sensor feedback and microcontroller logic. It is ideal for home gardeners, hydroponic setups, or anyone who wants to ensure their plants get consistent watering without manual intervention.

## Features

- **Automated Watering:** Uses soil moisture sensors to detect when plants need water and activates a pump or valve accordingly.
- **Customizable Thresholds:** Configure moisture levels at which watering should start/stop.
- **Low Maintenance:** Reduces the need for daily watering and helps prevent over- or under-watering.
- **Expandable:** Can be adapted for multiple plants or zones.
- **Microcontroller-Based:** Typically runs on Arduino, ESP32, Raspberry Pi, or similar hardware.

## How It Works

1. **Monitoring:** Soil moisture sensors continuously monitor the moisture level of the soil.
2. **Decision Logic:** The microcontroller reads sensor data and compares it to user-defined thresholds.
3. **Watering:** If the soil is dry, the microcontroller activates a pump or solenoid valve to deliver water to the plant.
4. **Feedback:** The system stops watering once desired moisture is reached.

## Typical Hardware Components

- Microcontroller (e.g., Arduino, ESP32, Raspberry Pi)
- Soil moisture sensor(s)
- Relay module or transistor circuit
- Water pump or solenoid valve
- Water source (reservoir or tap)
- Tubing for water delivery
- Power supply

## Example Wiring Diagram

```
[ Sensor ] -- [ Microcontroller ] -- [ Relay ] -- [ Pump ]
```

## Getting Started

1. **Clone this repository:**
    ```bash
    git clone https://github.com/chengat/self-watering-system.git
    ```
2. **Assemble hardware** according to the wiring diagram.
3. **Configure software:** Edit code files to adjust thresholds and hardware pin assignments as needed.
4. **Upload code** to your microcontroller.
5. **Test the system** with your plants.

## Customization

You can expand and modify the system to support:
- Multiple plant zones
- Advanced scheduling (e.g., only water during certain times)
- Integration with smart home systems
- Monitoring via web dashboard or mobile app

## License

This project is open source under the MIT License.

---

**Happy growing!**
