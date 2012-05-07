module ApplicationHelper
  def selected_class (but)
    case but
      when 'home'
        return "selected" if controller.controller_name == "home" and  controller.action_name == "index"
      when 'projects'
        if controller.controller_name == "projects" and  controller.action_name == "index"  or  controller.action_name == "show" or controller.action_name == "description" or controller.action_name == "files" or controller.action_name == "additionalDetails" or controller.action_name == "bugs"
          return "selected"
        end
      when "contacts"
        return "selected" if controller.controller_name == "contacts" and  controller.action_name == "contact"
      else
        return "selected" if controller.controller_name == but
    end
  end
end
