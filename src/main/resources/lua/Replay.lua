local log = require("log")
local Api = require("coreApi")
local json = require("json")
local http = require("http")

function ReceiveFriendMsg(CurrentQQ, data)
    return 1
end
function ReceiveGroupMsg(CurrentQQ, data)
	text = data.Content
	text = string.gsub(text," ","")
	text = string.gsub(text,"曰","日")
	text = string.gsub(text,"","")
    if (string.find(text, "日日") == 1 and data.FromUserId ~= 0) then
        luaRes =
            Api.Api_SendMsg(
            CurrentQQ,
            {
                toUser = data.FromGroupId,
                sendToType = 2,
                sendMsgType = "TextMsg",
                groupid = 0,
                content = "日日"..data.FromNickName,
                atUser = 0
            }
        )
    end
    return 1
end
function ReceiveEvents(CurrentQQ, data, extData)
    return 1
end