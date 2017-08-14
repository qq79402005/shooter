extends Node

var file_name = "user://apps/shooter/pw.bin"
var config_file = null
var account = ""
var password = ""

func _ready():
	config_file = ConfigFile.new()
	if config_file.load(file_name) == OK:
		account = config_file.get_value("data", "account", "")
	
func get_account():
	return account
	
func register_account():
	var unique_id = OS.get_unique_ID()
	if unique_id == "":
		pass
	else:
		pass
		
func login():
	var unique_id = OS.get_unique_ID()
	if unique_id == "":
		# 弹出登录界面
		get_node("login").set_hidden(false)
	else:
		# 根据 OS unique_id 生成账号
		HTTPClient
			
func game_room_match():
	print("search enemy")

# 快速匹配
func _on_vs_online_pressed():
		# 当前是否有ID
	var account = get_account()
	if account=="":
		login()
	else:
		game_room_match()
