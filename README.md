[![GitHub License](https://img.shields.io/badge/license-MIT-green.svg)](https://opensource.org/licenses/MIT)
![Version](https://img.shields.io/badge/version-1.0.4-blue.svg)
# Json-2-Env
The JSON to Env plugin simplifies the process of converting JSON strings into environment variable files. With this tool, you can effortlessly convert multiple JSON objects at once, streamlining your development workflow.
### Key Features:
- **Batch Conversion:** Convert multiple JSON strings into environment variables simultaneously, saving time and effort.
- **Customizable Prefixes:** Easily add custom prefixes to individual keys for better organization and clarity in your environment variable files.
Whether you're managing configurations for different environments or integrating with various services, the JSON to Env plugin makes it quick and easy to generate the necessary environment variables directly from your JSON data.
## Project Setup.
### Prerequisite
1. Install the plugin **[Plugin DevKit](https://plugins.jetbrains.com/plugin/22851-plugin-devkit)** in IntelliJ:
2. Clone the project repository:
   ```bash
   git clone https://github.com/Satya190597/json-2-env.git
   ```
### Run the project.
- Open project in intelliJ:
- Build the project using following commnad:
  ```bash
  gradle build
  ```
- Run the project to test the plugin locally:
  ```bash
  gradle runIde
  ```
## How to use?
![How To Use](https://i.imgur.com/ac2V9k2.png)
### Analyze Nested JSON Structure. (Optional)
- Enabling this option will flatten the nested JSON structure into individual key-value pairs, rather than retaining it as a single string.
![How To Use](https://i.imgur.com/ddFS11U.png)
### Analyze array as multi value.
- By selecting this option, all the elements in the array will be treated as individual key-value pairs.
![How To Use](https://i.imgur.com/JU6y2oQ.png)
## How to Raise an Issue?
When raising an issue, please ensure to include all relevant details and the steps to reproduce the defect, if applicable. To help you categorize your request correctly, refer to the table below, which outlines the appropriate labels to use for different types of issues.

| Issue Type                     | Label Used               |
|--------------------------------|-------------------------|
| For any kind of functional enhancement.                    | ![ENHANCEMENT](https://img.shields.io/badge/enhancement-cyan)            |
| For any defect or bug.                  |  ![BUG](https://img.shields.io/badge/bug-red)|
| Enhancement in documentation.    | ![DOCUMENTATION](https://img.shields.io/badge/documentation-blue)           |
| Request for help on plugin Usage. | ![HELP WANTED](https://img.shields.io/badge/help%20wanted-green)           |





