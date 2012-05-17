class AnneesController < ApplicationController
  before_filter :authorize

  def publier
    @annee = Annee.find(params[:id])
    @annee.invisible_annee = FALSE
    @annee.save
    redirect_to(annees_path)
  end

  def horsligne
    @annee = Annee.find(params[:id])
    @annee.invisible_annee = TRUE
    @annee.save
    redirect_to(annees_path)
  end

  # GET /annees
  # GET /annees.xml
  def index
    @annees = Annee.all

    respond_to do |format|
     format.html # index.html.erb
      format.xml  { render :xml => @annees }
    end
  end

  def show
    @annee = Annee.find(params[:id])
    @title = @annee.year
  end

  # GET /annees/new
  # GET /annees/new.xml
  def new
    @annee = Annee.new

    respond_to do |format|
      format.html # new.html.erb
      format.xml  { render :xml => @annee }
    end
  end

  # GET /annees/1/edit
  def edit
    @annee = Annee.find(params[:id])
  end

  # POST /annees
  # POST /annees.xml
  def create
    @annee = Annee.new(params[:year])

    respond_to do |format|
      if @annee.save
        #flash[:notice] = 'Année créée'
        format.html { redirect_to(annees_path) }
        format.xml  { render :xml => @annee, :status => :created, :location => @annee }
      else
        format.html { render :action => "new" }
        format.xml  { render :xml => @annee.errors, :status => :unprocessable_entity }
      end
    end
  end


  # PUT /annees/1
  # PUT /annees/1.xml
  def update
    @annee = Annee.find(params[:id])

    respond_to do |format|
      if @annee.update_attributes(params[:year])
        #flash[:notice] = 'Année mise à jour.'
        format.html { redirect_to(annees_path) }
        format.xml  { head :ok }
      else
        format.html { render :action => "edit" }
        format.xml  { render :xml => @annee.errors, :status => :unprocessable_entity }
      end
    end
  end

end
