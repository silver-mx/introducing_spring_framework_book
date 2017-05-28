@Controller
class MyApp {

	@RequestMapping("/")
	@ResponseBody
	String message() {
		return "<H1>Hello World Groovy - SpringBoot !</H1>"
	}
}